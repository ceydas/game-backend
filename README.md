# Dream Games Backend Study
Backend Case Study for Dream Games. The case involves user registration and initialization, level and coin balance updates, real-time leaderboard updates, and unit tests. 

## Introduction
In order to solve the given problem, I had to consider the system’s ability to scale since a system with 10M+ daily active users and 10K+ RPS requires high availability and high performance. If this were to become a real production level application, I would consider implementing a microservice architecture for its benefits in scaling. However, since this application is fairly simple with 5-6 “modules” that are highly related to each other with frequent communication needs, I opted for a modular-monolithic approach. Given the time constraint of 10 days, I prioritized system architecture design, data design, entity-relationship design and implementing the core features like user creation, tournament start and end, update user level and coins, enter tournaments (create tournament groups with users from different countries and get payment) using kafka topic enter-tournament. 
<br/>

The current version of the project can be improved by adding a load balancer before the several instances of the application. Database replication may be required for such a high scale application. Adding unit tests, adding logging, and adding a kafka topic for update-level can be important since it will be heavily invoked especially during tournament hours. Handling or adding retry logic for failed operations is also important. 

## Overview
The implementation consists of 5 modules:
1) User
2) Tournament
3) Matchmaking
4) TournamentSession
5) Leaderboard


Each module consists of the following layers:
1) Controller
2) Service, EntityService
3) Entity
4) Repository
5) Dto
6) Enums
7) Exception


## Quick-start

- Clone this repository.
- Modify the docker-compose.yaml file using your credentials wherever needed.
- Run `docker-compose up --build`
- Test the endpoints using `http://localhost:8080/swagger-ui/index.html`

## Architecture Diagram & Design Decisions


The following diagram shows the intended architecture diagram. In the actual implementation, I was not able to integrate a load balancer, and only "enter-tournament" exists as a kafka topic.
<img width="1332" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/de67af6b-d3f8-4e38-9a1f-924e9afcab66">

<br/>
The kafka partition design is as follows, by increasing the amount of application instances deployed, we can consume messages with higher throughput.
<img width="1249" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/716c8834-4621-4bec-856d-b040d5ba06d4">

## Data Design
![image](https://github.com/ceydas/dream-backend-case-study/assets/26047050/640ac417-2843-4f09-9dc6-01718aea683e)

## API Endpoints
#### Overview: Includes all requests excluding claim reward.
<img width="726" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/c101463a-abc3-4ef0-ac4b-5ea55448e0d9">

#### Create User : This request creates a new user, returning a unique user ID, level,coins, and country.
POST http://localhost:8080/user-progress/users/register
<img width="1447" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/52c28d7c-120a-4008-a040-1f134c9937f5">

#### Update Level: This request is sent by the client after each level completion. It updates the user's level and coins. If there's an active tournament, it updates the leaderboard. Returns updated progress data. 
PUT http://localhost:8080/user-progress/users/{id}/complete-level
<img width="1443" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/1a45080f-485b-44c0-b2ec-7195db026f66">

#### Enter Tournament: This request allows a user to join the current tournament and returns user data.
POST http://localhost:8080/tournament/enter/{id}
<img width="1439" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/01b2b385-a314-4abf-8552-2b2e4e629632">

#### Get Country Leaderboard
<img width="1411" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/9bf700b6-8fa0-4738-a36c-ad158be9d388">

#### Get Group Leaderboard
**Request**: http://localhost:8080/leaderboard/group/1

**Response**: <br/>
[
  {
    "score": 6,
    "value": "user 1:country France"
  },
  {
    "score": 3,
    "value": "user 5:country United Kingdom"
  },
  {
    "score": 1,
    "value": "user 9:country United States"
  }
]

#### Get Group Rank
**SwaggerUI**:
<img width="1445" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/dfe4f2f5-34b5-48b2-a20b-1696731b6716">
<br/>
**Redis sorted set visualization**:
<img width="419" alt="image" src="https://github.com/ceydas/dream-backend-case-study/assets/26047050/7de6f516-b641-4f2b-94da-1bed199a2b24">
