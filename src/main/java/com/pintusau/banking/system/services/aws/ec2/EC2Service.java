package com.pintusau.banking.system.services.aws.ec2;

import com.pintusau.banking.system.entities.EC2Instance;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.internal.util.EC2MetadataUtils;

@Service
public class EC2Service {

  public EC2Instance getCurrentInstanceInfo() {
    EC2MetadataUtils.InstanceInfo instanceInfo = EC2MetadataUtils.getInstanceInfo();
    return new EC2Instance(instanceInfo.getInstanceId(),
        instanceInfo.getInstanceType(),
        instanceInfo.getAvailabilityZone(),
        instanceInfo.getArchitecture());
  }
}
