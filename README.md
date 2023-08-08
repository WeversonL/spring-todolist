<h1 align="center">
   Spring Todolist 
</h1>

API to manage tasks (Todolist) made in Spring Boot for programming practices.

## Technologies
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/)
- [H2](https://www.h2database.com/html/main.html)
- [Actuator](https://spring.io/guides/gs/actuator-service/)
- [Docker](https://docs.docker.com/get-started/)
- [Github Workflow](https://docs.github.com/en/actions/using-workflows)

## Practices adopted

- SOLID
- API REST
- Queries with Spring Data JPA
- Dependency Injection
- Automated tests
- Containerization with docker
- Error response handling
- Automatic Swagger Generation with OpenAPI 3

## Get started

0. Clone git repository

        git clone https://github.com/WeversonL/spring-todolist.git
        cd spring-todolist

### Running the application with docker-compose

1. Start with docker-compose

        docker-compose up -d

2. If you want to view the interactive swagger, access the url below in your browser. For more usage information, access the guide <a href="#usage">usage</a>

        http://localhost:8080/swagger-ui/index.html#/

The API can be accessed at [localhost:8080](http://localhost:8080).
Swagger can be viewed at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)

## API Endpoints

To make the HTTP requests below, the [httpie](https://httpie.io) tool was used:

- Create Task 
```
$ http POST :8080/todos name="title" description="description task" priority=1

[
  {
    "id": 1,
    "name": "title",
    "description": "description task",
    "priority": 1,
    "realized": false
  }
]
```

- List Tasks
```
$ http GET :8080/todos

[
  {
    "id": 1,
    "name": "title",
    "description": "description task",
    "priority": 1,
    "realized": false
  }
]
```

- List Paged Tasks

```
$ http GET ':8080/todos/pages?page=0&size=5'

[
    {
        "content": [
            {
                "id": 1,
                "name": "title",
                "description": "description task",
                "priority": 1,
                "realized": false
            }
        ],
        "empty": false,
        "first": true,
        "last": true,
        "number": 0,
        "numberOfElements": 1,
        "pageable": {
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 5,
            "paged": true,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "unpaged": false
        },
        "size": 5,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "totalElements": 1,
        "totalPages": 1
    }
]
```

- Update Task
```
$ http PUT :8080/todos/1 name="new title" description="new description task" priority=2 realized=true

[
  {
    "id": 1,
    "name": "new title",
    "description": "new description task",
    "priority": 2,
    "realized": true
  }
]
```

- Remove Task
```
http DELETE :8080/todos/1

[ ]
```

## License

`Spring Todolist` is [MIT licensed](LICENSE).