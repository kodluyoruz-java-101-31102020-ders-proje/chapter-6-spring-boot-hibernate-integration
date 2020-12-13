# chapter-6-spring-boot-hibernate-integration
Spring and Hibernate Integration via SessionFactory

Docker kurmak için aşağıdaki linkten faydalanabilirsiniz.

https://docs.docker.com/get-docker/

Docker ile veritabanlarını bilgisayarınızda ayağa kaldırmak için aşağıdaki komutları kullanabilirsiniz. Bu komutları bilgisayarınızın terminalinde çalıştırmalısınız.

MySQL
docker run -d --name mysqltest -p 3306:3306 -v ~/your_own_folder:/var/lib/mysql -u root  -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

Postgres
docker run -d --name my_postgres -v ~/your_own_folder:/var/lib/postgresql/data -u postgres -e POSTGRES_PASSWORD=root -p 54320:5432 postgres:latest

Mongo
docker run -d -p 27017:27017 -v ~/your_own_folder:/data/db mongo
