# API Para gerenciar vendas e vendedores

## Como usar:

Existe um script para inserir 2 vendedores iniciais assim que a aplicação iniciar.

1 - Criar uma venda

POST - http://localhost:8080/api/v1/sales

BODY -
```
{
"saleDate": "2023-11-21",
"saleValue": 1,
"sellerId": 1
}
```
RESPOSTA ESPERADA -
```
{
"id": 1,
"saleDate": "2023-11-21"
}
```

2 - Buscar vendedores
GET - http://localhost:8080/api/v1/sellers?startDate=2023-11-21&endDate=2023-11-21

RESPOSTA ESPERADA - 
```
[
    {
        "name": "jose",
        "salesAmount": 1,
        "salesDailyAverage": 1
    },
    {
        "name": "maria",
        "salesAmount": 0,
        "salesDailyAverage": 0
    }
]
```