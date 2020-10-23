
# Spring Boot MicroServices with Kubernetes on Google Cloud

This example shows how to create a REST APIS and then deploy it on Google Cloud with Kubernetes

**Prerequisites:** [Java 8](https://adoptopenjdk.net/)

 [Google Cloud and GKE](https://cloud.google.com/)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/mozammal/gcloud-kubernetese-spring-boot.gitt
cd gcloud-kubernetese-spring-boot
```

Start the Spring Boot app using the following command:

```bash

mvn clean package -Dmaven.test.skip=true

gcloud compute disks create --size=10GiB --zone=us-central1-a mongodb

gcloud container clusters list

gcloud container clusters get-credentials demo-spring-boot-cluster --zone us-central1-a --project {your_project_name}

kubectl apply -f deployment-mongo.yaml

docker build --tag person-boot-app-gcloud:1.0 .

docker tag  person-boot-app-gcloud:1.0 gcr.io/{your_project_name}/person-boot-app-gcloud:1.0

gcloud docker -- push gcr.io/{your_project_name}/person-boot-app-gcloud:1.0

kubectl apply -f deployment-person-service.yml

kubectl apply -f gcloud-ingress.yaml
```

Delete the Kubernetes cluster using the following command:

```bash
gcloud container clusters delete demo-spring-boot-cluster --zone us-central1-a
```

How to get external ip address $IP_ADDTESS_FROM_GOOGLE from  outside

```bash
kubectl get ingress
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