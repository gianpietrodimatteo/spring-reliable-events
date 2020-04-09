curl localhost:8080/user/add -d name=First -d email=someemail@someemailprovider.com
# Saved
curl 'localhost:8080/user/all'
# [{"id":1,"name":"First","email":"someemail@someemailprovider.com"}]
