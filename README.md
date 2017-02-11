# simple-article-store
Simple article store

## Deploy
### Create stack
```
aws cloudformation create-stack --capabilities CAPABILITY_IAM --stack-name article-app --template-body file://cfn.yaml
```