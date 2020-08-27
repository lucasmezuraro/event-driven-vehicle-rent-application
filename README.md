# Event Driven - Vehicle Rent Application


## Architecture visualization

[![N|Solid](https://github.com/lucasmezuraro/event-driven-vehicle-rent-application/blob/master/images/RentaCarAchitecture.png)]

This architeture was built with purpose of studying the event driven architeture, i worked basicly with Stream Cloud abstraction together RabbitMQ. Besides that, i added Prometheus and Grafana for catch container information for analysis, then was included Actuator for get the metrics of each individual application. For tracing was used Zipkin with Sleuth, database was used Postgres under containers, just that. About Cache, i spent sometime finding how it works and the solution is a bit of much tutorials that a found on web. 

#### So, the summary of libs/dependecies inside this application is:

  - Spring Cloud Stream
  - Spring Cloud Eureka
  - Spring Cloud Hystrix
  - Spring Cloud Sleuth
  - Spring Boot Actuator
  - Spring Data Redis
  - Spring Boot Core
  - Zipkin
  - Prometheus
  - Grafana
  - Postgres
  - Docker
##### Sorry, i didn't tests, i knew, but sorry, i swear, them comming soon !

#
#

### Main feature of application:
  - You can rent a car for a time you want, then a event will dispatched for check you payment on the payment service, if it's ok, your selected car will be prepared on preparation service.

### Renting a car...

##### create a locator
#
```
method: POST
http://localhost:8900/reserve/
{
	"locator": {
    "name": "Lucas",
    "lastName": "Pereira",
    "cpf": "000000000",
    "rg": "00000000",
    "cellPhone": "0000000"
  }
}
```

##### creating a car
#
```
method: POST
http://localhost:8900/car/
{
	"car": {
		"name": "Uno",
		"dayPrice": 84.90,
		"year": "2018/2019",
		"carBrand": "FIAT",
		"available": true,
		"vehiclePlate": "AAA0000"
	}
}
```

##### creating a reservation
#
```
method: POST
http://localhost:8900/reserve/
{
  "carId": 1,
  "locatorId": 1,
		"reserveDuration": {
			"daysReserve": 5,
			"startAt": "2020/08/22",
			"endAt": "2020/08/27"
		},
		"pmtInfo": {
			"name": "THVjYXM=",
			"cpf": "OTE4MjM5ODM5MTI4Mw==",
			"number": "OTgxMi04MzkwLTkxMjMtMzg5MQ==",
			"yearValid": "MjAyMA=="
		}
}
```

[![N|Solid](https://github.com/lucasmezuraro/event-driven-vehicle-rent-application/blob/master/images/Rent_action.png)]

### Installation

```sh
$ cd event-driven-vehicle-rent-application/
$ docker-compose up --build
```


License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
