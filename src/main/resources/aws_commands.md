**Task 1**  
INLINE:
- get-role-policy:
  ```  
  aws iam get-role-policy --role-name pintusau@EC2Role --policy-name pintusau@S3Policy  
  aws iam list-role-policies --role-name pintusau@EC2Role
  ``` 
  
MANAGE:
- get-policy:
  ```  
  aws iam get-policy --policy-arn arn:aws:iam::993042411437:policy/pintusau@S3Policy
  ```
- get-policy-version:  
  ```
  aws iam get-policy-version --policy-arn arn:aws:iam::993042411437:policy/pintusau@S3Policy --version-id v1
  ```
- list-attached-role-policies:
  ```  
  aws iam list-attached-role-policies --role-name pintusau@EC2Role
  ```  
___  
**Task 2** 

- CREATE A BUCKET:
  ```  
  aws s3 mb s3://banking-system-repo --region eu-west-1  
  aws s3api create-bucket --bucket banking-system-repo --region eu-west-1 --create-bucket-configuration LocationConstraint=eu-west-1  
  
  aws s3 mb s3://banking-system-entities --region eu-west-1  
  aws s3api create-bucket --bucket banking-system-entities --region eu-west-1 --create-bucket-configuration LocationConstraint=eu-west-1  
  ``` 
- ADD VERSIONING:
  ```  
  aws s3api put-bucket-versioning --bucket banking-system-repo --versioning-configuration Status=Enabled
  
  aws s3api put-bucket-versioning --bucket banking-system-entities --versioning-configuration Status=Enabled
  ```   
- ADD PUBLIC ACCESS BLOCK:  
  ```  
  aws s3api put-public-access-block --bucket banking-system-repo --public-access-block-configuration BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true
  
  aws s3api put-public-access-block --bucket banking-system-entities --public-access-block-configuration BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true
  ```  
- ADD TAGS:  
  ```  
  aws s3api put-bucket-tagging --bucket banking-system-repo --tagging "TagSet=[{Key=user,Value=dzmitry.pintusau}]"
  
  aws s3api put-bucket-tagging --bucket banking-system-entities --tagging "TagSet=[{Key=user,Value=dzmitry.pintusau}]"  
  ```  
- UPLOAD:  
  ```  
  aws s3api put-object --bucket banking-system-repo --key banking-system.jar --body /Users/dzmitry.pintusau/GIT/AWS/aws-training/target/banking-system.jar
  ```   
- LIST FILES:  
  ```  
  aws s3 ls s3://banking-system-repo 
  
  aws s3 ls s3://banking-system-entities
   ```   
___  
**Task 3** 

- installed java 11 corretto
- installed apache httpd
- added virtual host for 7777

**USER DATA SCRIPT (for every EC2 restart):**
  ```   
Content-Type: multipart/mixed; boundary="//"
MIME-Version: 1.0

--//
Content-Type: text/cloud-config; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="cloud-config.txt"

#cloud-config
cloud_final_modules:
- [scripts-user, always]

--//
Content-Type: text/x-shellscript; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="userdata.txt"

#!/bin/bash
aws s3 cp s3://pintusau-banking-system-repo/banking-system.jar /tmp
java -jar /tmp/banking-system.jar
--//  
  ```   
**Configure CloudWatch:**  
  ``` 
sudo wget https://s3.eu-central-1.amazonaws.com/amazoncloudwatch-agent-eu-west-1/amazon_linux/amd64/latest/amazon-cloudwatch-agent.rpm
sudo rpm -U ./amazon-cloudwatch-agent.rpm
sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-config-wizard
sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m ec2 -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json -s
mkdir /usr/share/collectd
cd /usr/share/collectd
touch types.db  
  ```
