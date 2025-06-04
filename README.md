# 👨‍💻 ConectaPro API 
Repositório Back-End do meu projeto para TCC 🚀

##💡 Ideia principal da ConectaPro
A ConectaPro é um projeto que interliga empresas com prestadores de serviço autonômos.

## 🤔 Por que esse projeto?
Analisando a ODS 9.0 da ONU, eu e meu grupo reparamos que globalmente, muitas empresas sofrem com falta de infraestrutura adequada para realizar contratação de serviços para a realização de manutenções nos seus departamentos.

## 🎯 Objetivo da ConectaPro
Temos como objetivo facilitar que as empresas contatem prestadores e realizem acordos de prestação de serviços e assim ambos consigam maior facilidade e agilidade na hora de solucionar problemas.



API RESTful desenvolvida em **Java Spring Boot** para gerenciar uma plataforma de conexão entre **empresas** e **prestadores de serviço**.
Utilizado spring framework juntamente com o padrão de arquitetura Model-Controller-Service

## 🌐 Deploy

A API está hospedada no **Azure App Service**.  
> 🔗 URL da API: `https://conectapro-api.azurewebsites.net`

---

## 🛠️ Tecnologias

- Java 17
- Spring Boot
- Spring Security (com JWT)
- Spring Data JPA
- Hibernate
- Azure App Service
- SQL Server / MySQL
- GitHub Actions (CI/CD)
- Swagger (documentação da API)

---

## 🔐 Autenticação

A autenticação é feita via **JWT (JSON Web Token)**.  
Os endpoints protegidos requerem um token válido no header: **Authorization: Bearer <token>**



