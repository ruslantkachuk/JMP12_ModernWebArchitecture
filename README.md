Microservices architecture 
-----------------------
Application based on https://github.com/paulc4/microservices-demo

Additional services are created:

**Catalog**: port 4444

**Cart**: port 5555

#### How to run:
**Step 1**: clone project and build: gradle build

**Step 2**: run all modules step by step by command - gradle bootRun:
   - registration
   - account
   - catalog
   - cart
   - web

**Step 3**: open localhost:3333 and start to test application 


Pros and cons of usage SOA vs MicroServices 
-----------------------

####SOA advantages
- Service Reusability.
In SOA, an application is built by assembling small, self-contained, and loosely coupled pieces of functionality. Therefore, the services can be reused in multiple applications independent of their interactions with other services.

- Easy Maintainability.
Since a service is an independent entity, it can be easily updated or maintained without having to worry about other services. Large, complex applications can thus be managed easily.

- Greater Reliability.
SOA-based applications are more reliable since small, independent services are easier to test and debug as compared to massive chunks of code.

- Location Independence.
The services are usually published to a directory where consumers can look them up. This approach allows a service to change its location at any time. However, the consumers are always able to locate their requested service through the directory look up.

- Improved Scalability and Availability.
Multiple instances of a single service can run on different servers at the same time. This increases scalability and availability of the service.

- Improved Software Quality.
Since services can be reused, there is no scope for redundant functionality. This helps reduce errors due to inconsistent data, and thereby improves the quality of code.

- Platform Independence.
SOA facilitates the development of a complex product by integrating different products from different vendors independent of the platform and technology.

- Increased Productivity.
Developers can reuse existing legacy applications and build additional functionality without having to develop the entire thing from scratch. This increases the developers' productivity, and at the same time, substantially reduces the cost of developing an application.

####SOA disadvantages

- Increased Overhead.
Every time a service interacts with another service, complete validation of every input parameter takes place. This increases the response time and machine load, and thereby reduces the overall performance.

- Complex Service Management.
The service needs to ensure that messages have been delivered in a timely manner. But as services keep exchanging messages to perform tasks, the number of these messages can go into millions even for a single application. This poses a big challenge to manage such a huge population of services.

- High Investment Cost.
Implementation of SOA requires a large upfront investment by means of technology, development, and human resource.

####MicroServices advantages
- Each microservice is relatively small
- Easier for a developer to understand
- The IDE is faster making developers more productive
- The application starts faster, which makes developers more productive, and speeds up deployments
- Each service can be deployed independently of other services - easier to deploy new versions of services frequently
- Easier to scale development. It enables you to organize the development effort around multiple teams. Each (two pizza) team is owns and is responsible for one or more single service. Each team can develop, deploy and scale their services independently of all of the other teams.
- Improved fault isolation. For example, if there is a memory leak in one service then only that service will be affected. The other services will continue to handle requests. In comparison, one misbehaving component of a monolithic architecture can bring down the entire system.
- Each service can be developed and deployed independently
- Eliminates any long-term commitment to a technology stack. When developing a new service you can pick a new technology stack. Similarly, when making major changes to an existing service you can rewrite it using a new technology stack.

####MicroServices disadvantages
- Developers must deal with the additional complexity of creating a distributed system.
- Developer tools/IDEs are oriented on building monolithic applications and don’t provide explicit support for developing distributed applications.
- Testing is more difficult
- Developers must implement the inter-service communication mechanism.
- Implementing use cases that span multiple services without using distributed transactions is difficult
- Implementing use cases that span multiple services requires careful coordination between the teams
- Deployment complexity. In production, there is also the operational complexity of deploying and managing a system comprised of many different service types.
- Increased memory consumption. The microservice architecture replaces N monolithic application instances with NxM services instances. If each service runs in its own JVM (or equivalent), which is usually necessary to isolate the instances, then there is the overhead of M times as many JVM runtimes. Moreover, if each service runs on its own VM (e.g. EC2 instance), as is the case at Netflix, the overhead is even higher.
