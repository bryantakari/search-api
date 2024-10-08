


# Geonames API Documentation

## Base URL
### Localhost
Requirement: [Docker]
Run docker-compose up and hit the api in this endpoint:
```
localhost:8080/suggestions?q=Austin&longitude=34.052235&latitude=-74.005974&strategy=DescendingStrategy
```

Request:
q : partial query name

longitude : double data type for longitude

latitude : double data type for latitude

strategy : Available Strategy Right now : [AscendingStrategy | DescendingStrategy]

Please use DescendingStrategy for the question requirement precision


