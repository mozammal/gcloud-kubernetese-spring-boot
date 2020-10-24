
# Spring Boot MicroServices with Kubernetes on Google Cloud

This example shows how to create a REST APIS and then deploy it on Google Cloud with Kubernetes

**Prerequisites:** [Java 8](https://adoptopenjdk.net/)

 [Google Cloud and GKE](https://cloud.google.com/)


## Before you begin

1. Create a new
[Cloud Platform project](https://console.cloud.google.com/projectcreate).

1. [Enable billing](https://support.google.com/cloud/answer/6293499#enable-billing)
   for your project.

1.  Download and install the [Google Cloud
    SDK](https://cloud.google.com/sdk/docs/), which includes the
    [gcloud](https://cloud.google.com/sdk/gcloud/) command-line tool.

1.  Initialize the Cloud SDK.

        gcloud init

2.  Set your default project (replace YOUR-PROJECT-ID with the name of your
    project).

        gcloud config set project YOUR-PROJECT-ID
        
3. configure Docker command-line tool to authenticate to Container Registry (you need to run this only once):
   
        gcloud auth configure-docker        

4. Enable the Container Engine API

In most cases, the gcloud endpoints services deploy command enables these required services.

To confirm that the required services are enabled:

    gcloud services list

If you don't see the required services listed, enable them:

    gcloud services enable SERVICE_NAME

Replace `SERVICE_NAME` with the name of the service to enable. 

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/mozammal/gcloud-kubernetese-spring-boot.gitt
cd gcloud-kubernetese-spring-boot
```

Start the Spring Boot app using the following command:

```bash

gcloud config set project [YOUR-PROJECT-ID]

mvn clean package -Dmaven.test.skip=true

gcloud compute disks create --size=10GiB --zone=us-central1-a mongodb

gcloud container clusters create demo-spring-boot-cluster --zone=us-central1-a --num-nodes=3 

gcloud container clusters list

gcloud container clusters get-credentials demo-spring-boot-cluster --zone us-central1-a --project [YOUR-PROJECT-ID]

kubectl apply -f deployment-mongo.yaml

docker build --tag person-boot-app-gcloud:1.0 .

docker tag  person-boot-app-gcloud:1.0 gcr.io/[YOUR-PROJECT-ID]/person-boot-app-gcloud:1.0

gcloud docker -- push gcr.io/[YOUR-PROJECT-ID]/person-boot-app-gcloud:1.0

kubectl apply -f deployment-person-service.yml
```

Delete the Kubernetes cluster using the following command:

```bash
gcloud container clusters delete demo-spring-boot-cluster --zone us-central1-a
```

How to get EXTERNAL_IP address $IP_ADDTESS_FROM_GOOGLE that we can use to make REST calls to 
our sample REST APIS if you happen to use LodBalancer

```bash
kubectl get service person-service
```

##### Create a Person 
 
- [rest endpoint](http://$IP_ADDTESS_FROM_GOOGLE/persons)

- Method: POST
- example JSON payload
----

```json
{
 "firstname": "Mozammal",
 "lastname": "Hossain"
}
```

##### return person by id 
 
- [rest endpoint](http://$IP_ADDTESS_FROM_GOOGLE/persons/id)

- endpoint: http://$IP_ADDTESS_FROM_GOOGLE/persons/{id} where id is the person  id
- Method: GET 
- example url: http://$IP_ADDTESS_FROM_GOOGLE/persons/abb120xqw122

##### return all person 
 
- [rest endpoint](http://$IP_ADDTESS_FROM_GOOGLE/persons)

- endpoint: http://$IP_ADDTESS_FROM_GOOGLE/persons
- Method: GET
- example url: http://$IP_ADDTESS_FROM_GOOGLE/persons

##### update a person 
 
- [rest endpoint](http://$IP_ADDTESS_FROM_GOOGLE/persons/{id})

- endpoint: http://$IP_ADDTESS_FROM_GOOGLE/persons/{id} where id is the person id
- Method: PUT
- example url:  http://$IP_ADDTESS_FROM_GOOGLE/persons/xabba1271
- example payload
----
```json
{
 "firstname": "Mohammed",
 "lastname": "Haque"
}
```

##### delete a person by id 
 
- [rest endpoint](http://$IP_ADDTESS_FROM_GOOGLE/persons/{id})

- endpoint: http://$IP_ADDTESS_FROM_GOOGLE/persons/{id} where id is the email id
- Method: DELETE
- example url:  http://$IP_ADDTESS_FROM_GOOGLE/persons/accac1331adad


## License
Apache 2.0, see [LICENSE](LICENSE).