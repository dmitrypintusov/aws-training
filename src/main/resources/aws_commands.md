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
	aws s3 mb s3://pintusau-banking-system-repository --region eu-central-1  
	aws s3api create-bucket --bucket pintusau-banking-system-repository --region eu-central-1 --create-bucket-configuration LocationConstraint=eu-central-1  
  ```  
- ADD PUBLIC ACCESS BLOCK:  
  ```  
	aws s3api put-public-access-block --bucket pintusau-banking-system-repository --public-access-block-configuration BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true
  ```  
- ADD TAGS:  
  ```  
	aws s3api put-bucket-tagging --bucket pintusau-banking-system-repository --tagging TagSet=[{Key=user,Value=dzmitry.pintusau}]  
  ```  
- UPLOAD:  
  ```  
	aws s3api put-object --bucket pintusau-banking-system-repository --key banking-system.jar --body D:\EPAM\AWS\AWS-PROJECT\target\banking-system.jar 
  ```   
- LIST FILES:  
  ```  
	aws s3 ls s3://pintusau-banking-system-entities/User/  
   ```   
___  
**Task 3** 

- installed java 8 corretto
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
aws s3 cp s3://pintusau-banking-system-repository/banking-system.jar /tmp
java -jar /tmp/banking-system.jar
--//  
  ```   
**Configure CloudWatch:**  
  ``` 
sudo wget https://s3.eu-central-1.amazonaws.com/amazoncloudwatch-agent-eu-central-1/amazon_linux/amd64/latest/amazon-cloudwatch-agent.rpm
sudo rpm -U ./amazon-cloudwatch-agent.rpm
sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-config-wizard
sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl -a fetch-config -m ec2 -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json -s
mkdir /usr/share/collectd
cd /usr/share/collectd
touch types.db  
  ``` 
 ___  
 **Task 4** 
 ___  
 **Task 5** 
   ``` 
 aws s3api put-object --bucket pintusau-banking-system-repository --key iac/pintusau-bs-template.json --body D:\EPAM\AWS\AWS-PROJECT\src\main\resources\iac\pintusau-bs-template.json
 https://pintusau-banking-system-repository.s3.eu-central-1.amazonaws.com/iac/pintusau-bs-template.json
 
 aws cloudformation deploy --template-file D:\EPAM\AWS\AWS-PROJECT\src\main\resources\iac\pintusau-bs-template.json --stack-name Pintusau-cf-stack --parameter-overrides CPUPolicyTargetValue=10 DefaultVPC=vpc-cfdd39a5 InstanceTCPPort=7777 KeyName=pintusau-key-pair SSHLocation=86.57.255.90/32 --tags user=dzmitry.pintusau
  ```  
 | Parameter  | Value |
 | ---------- | ----- |
 |CPUPolicyTargetValue|30
 |DefaultVPC|vpc-cfdd39a5		
 |InstanceTCPPort|7777
 |KeyName|pintusau-key-pair
 |SSHLocation|86.57.255.90/32
 
  ___  
  **Task 6** 
  ```   
 aws cloudformation validate-template --template-body file://D:\EPAM\AWS\AWS-PROJECT\src\main\resources\iac\pintusau-dynamo-db-user-template.json  
 aws cloudformation deploy --template-file D:\EPAM\AWS\AWS-PROJECT\src\main\resources\iac\pintusau-dynamo-db-user-template.json  --stack-name Pintusau-dynamo-db-user-stack --tags user=dzmitry.pintusau
 aws s3api put-object --bucket pintusau-banking-system-repository --key iac/pintusau-bs-template.json --body D:\EPAM\AWS\AWS-PROJECT-GIT\src\main\resources\iac\pintusau-bs-template.json
 aws cloudformation deploy --template-file D:\EPAM\AWS\AWS-PROJECT-GIT\src\main\resources\iac\pintusau-bs-template.json --stack-name Pintusau-cf-stack --parameter-overrides CPUPolicyTargetValue=30 DefaultVPC=vpc-cfdd39a5 InstanceTCPPort=7777 KeyName=pintusau-key-pair SSHLocation=86.57.255.90/32 --tags user=dzmitry.pintusau
 ``` 
  ___  
   **Task 7** 
  ```   
 aws cloudformation update-stack --template-body file://D:\EPAM\AWS\AWS-PROJECT-GIT\src\main\resources\iac\pintusau-bs-template.json --stack-name Pintusau-cf-stack --parameters ParameterKey=KeyName,UsePreviousValue=true ParameterKey=Insta
 nceTCPPort,UsePreviousValue=true ParameterKey=DefaultVPC,UsePreviousValue=true ParameterKey=CPUPolicyTargetValue,UsePreviousValue=true
 ```   