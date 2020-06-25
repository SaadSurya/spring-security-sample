# spring-security-sample
This sample is meant to replicate a scerio where there are three spring applications.
1) A spring application that will act as authorization server and will aslo contain a resource API (Resource Server).
2) A spring application that will act as client or business application that will implement business related APIs.
3) A spring application similar to client application that also incoorporate business requirements through APIs.

## Requirements
Purpose of this sample is to implement spring security in all three application to simulate below mentioned requirements.

1. Ability of client applications to communicate with each other and call each other’s Rest APIs.
2. Ability of client applications to communicate with Authorization Server application and call it’s Rest APIs.
3. Ability of client applications like IOS or Android based applications to be able to authenticate with the centeralized Authorization Server Application using grant_type of password and call Rest APIs of Client applications.

        Note: Detailed documentation is available in respective code file for further reference.
