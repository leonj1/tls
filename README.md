# Secure Java endpoint
## USAGE
```
./build.sh
docker-compose up
```

## VERIFICATION
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
