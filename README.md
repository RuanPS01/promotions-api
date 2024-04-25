# Promotions API

## Project

This project it's a web API to make CRUD operations about Supermarket scenario;

## Overview

This repository contains the solution implemented by the DM111 discipline from the Pos Graduation course Mobile and
Cloud Development by Inatel.

During the classes was implemented a web service to deal with a minimal structure of supermarket, i.e. The _CRUD_
operations for **Products**, **Users** _(Client and Admin)_, **Shopping Lists**. But now this API has CRUD operation for
**Promotions** and others manipulations;

To make these exposed APIs secure it was implemented an **Authentication** API as well using JWT token.

In addition to the auth API an _Interceptor_ was created and all the endpoints that required the authorization was
added behind this interceptor. It means, only authenticated users could access the resources and in some scenarios only
authenticated and authorized users were able to perform some operations.

## Integrations

The current project does have the integrations with: _Firebase DB_, _Firebase Cloud Message_, _Pub Sub_.

### Firebase

In order to reduce the cost and also reduce the risk of reach the Free tier limit of U$ 300 provided by the Google Cloud
I have decided to use Firebase as our main database solution. That decision helped us to reduce the number of resources
required to be deployed and simplify our deployment letting us move our focus on the code itself.

#### Remember
_You have to generate the service accounts from your Firebase project and paste into the right place, i.e.the folder_
_src/main/resources_

The following entities are stored on the Firebase:

> User
```json
{
  "email": "email@email.com.br",
  "id": "uuid",
  "name": "name",
  "password": "encrypted",
  "role": "ADMIN|CLIENT"
}
```

> Products
```json
{
  "id": "uuid",
  "name": "name",
  "amount": 0,
  "brand": "brand",
  "price": 5.99,
  "unit": "kg|lt|ml"
}
```

> Supermarket list
```json
{
  "id": "uuid",
  "name": "name",
  "products": [
    "product_id1",
    "product_id2"
  ]
}
```
> Promotion
```json
{
  "id": "uuid",
  "name": "name",
  "starting": "starting_date",
  "expiration": "expiration_date",
  "products": [
    {
      "productId": "product_id",
      "discount": 15
    }
  ]
}
```

## How to set up

In order to prepare the machine to either run or change the project follow the instructions detailed at
[DM111 setup instructions](DM111-setup.md)

## How to deploy to GAE

To deploy the code to **GAE** (_Google App Engine_), it is necessary first initialize the **gcloud** environment, please run
the following command:

```
gcloud init
```

During the execution of this command, it will be necessary to authenticate at the **Google Cloud** account. After the
initialization has been completed the deployment will be straightforward you only need to execute the following command.

```
gcloud app deploy --version 1 dm111/app.yaml
```

Some data/confirmation will be requested via terminal at the end of this step and as result of the deploy the link to access
the service via API will be there.

### Deploy multiple services at once

To deploy both services simultaneously, please run the following command:

```
gcloud app deploy --version 1 dm111/app.yaml dm111-promo/app.yaml
```
