package net.furikuri.repository;


import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import net.furikuri.domain.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("!mock")
public class DynamoDbArticleRepository implements ArticleRepository {

    private DynamoDB dynamoDb;

    @Value("${aws.table}") private String tableName;
    private final AmazonDynamoDBClient client;

    public DynamoDbArticleRepository() {
        client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(Regions.EU_CENTRAL_1));
        dynamoDb = new DynamoDB(client);
    }

    @Override
    public void save(Article article) {
        Table table = dynamoDb.getTable(tableName);
        table.putItem(
                new Item()
                        .withPrimaryKey("Id", article.getId())
                        .withString("Name", article.getName())
                        .withString("Image", article.getImage())
                        .withString("Description", article.getDescription())
                        .withString("Price", article.getPrice())
        );
    }

    @Override
    public List<Article> findAll() {
        return client
                .scan(tableName, Arrays.asList("Id", "Name", "Image", "Description", "Price"))
                .getItems().stream().map((item) ->
                        new Article(
                                item.get("Id").getS(),
                                item.get("Name").getS(),
                                item.get("Image").getS(),
                                item.get("Description").getS(),
                                item.get("Price").getS()
                        )
                ).collect(Collectors.toList());
    }
}
