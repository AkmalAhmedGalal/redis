# redis
this project covers all the incoming features:

Caching Database Queries
    -Implement a UserService that fetches users from a MySql database.
    
    -Use Redis to cache the user data so repeated queries return instantly.
    
    -Implement cache invalidation:
    
        -If a user updates their data, the cache should be updated/removed.
    
        -Expire the cache every 10 minutes.

2️⃣ API Rate Limiting with Redis
    -Implement an API endpoint: /api/messages/send.
    
    -Each user can only send 5 requests per minute.
    
    -If a user exceeds the limit, return an HTTP 429 Too Many Requests.
    
    -Store the request count in Redis with a TTL of 1 minute.

3️⃣ Distributed Locking with Redis
    -Implement a shared inventory system (e.g., flash sale).
    
    -Allow only one request at a time to update an inventory item using a Redis lock.
    
    -If another request tries to update while a lock is active, wait or fail gracefully.

Also, Redis Pub/Sub for real-time notifications when users update their profile.

Enhancement:
    this project needs to be centralized project and used for all caching purposes from the outer applications.
