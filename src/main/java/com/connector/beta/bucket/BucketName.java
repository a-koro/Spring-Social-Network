package com.connector.beta.bucket;

public enum BucketName {
    PROFILE_IMAGE("team-project-image-upload-1");

    private final  String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
