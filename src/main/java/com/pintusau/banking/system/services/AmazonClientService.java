package com.pintusau.banking.system.services;

import org.springframework.stereotype.Service;
import com.pintusau.banking.system.entities.EC2Instance;

import software.amazon.awssdk.regions.internal.util.EC2MetadataUtils;

@Service
public class AmazonClientService {

    public EC2Instance getCurrentInstanceInfo() {
        EC2MetadataUtils.InstanceInfo instanceInfo = EC2MetadataUtils.getInstanceInfo();
        return new EC2Instance(instanceInfo.getInstanceId(),
                instanceInfo.getInstanceType(),
                instanceInfo.getAvailabilityZone(),
                instanceInfo.getArchitecture());
    }
}