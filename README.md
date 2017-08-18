# Secure Java endpoint
## USAGE
```
./build.sh
docker-compose up
```

## VERIFICATION / INTEGRATION TEST
```
python ./test.py --domain localhost --port 5000 --cert-path src/main/resources/localhost.crt
https://localhost:5000/messages/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa correctly not found
https://localhost:5000/messages POSTed successfully
https://localhost:5000/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae correctly found
https://localhost:5000/messages POSTed successfully
https://localhost:5000/messages/fcde2b2edba56bf408601fb721fe9b5c338d10ee429ea04fae5511b68fbf8fb9 correctly found
https://localhost:5000/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae correctly found

***************************************************************************
All tests passed!
***************************************************************************
```

## TODO
```
- Unit tests
- Pass through YourKit for performance analysis
- Benchmark testing to find the breaking point of concurrent requests
```

# LIMITATIONS
```
Current implementation uses a ConcurrentHashMap to store hashes.
This is to ensure atomicity when handling separate requests at the same time.

Limitation 1. the map lives in this single instance of the JVM and data store is not replicated across other JVMs. Therefore horizontal scaling not possible for reads.
A: Consider Redis or memcache to store hashes. This should allow other instances of this app to be aware of new hashes.
Limitation 2. As-is, this app is responsible for lookups in the Map. Read can be improved by placing a CDN / Varnish cache ahead of this process to store read requests. Add some sane cache expiry.
```
