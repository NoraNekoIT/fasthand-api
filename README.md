# HOW TO RUN DOCKER
- Jika pada saat jalankan docker aplikasi muncul error 0xfffffff bisa di kill port 53 yg bikin docker berhenti
- Untuk ngecek port `netstat -a -o -n` 
- Untuk kill port `taskkill /F /PID namaPid`


- Jika target belum ada bisa compile dulu dengan cara
 `mvn clean package -u`


- Jalankan docker-compose untuk bikin image dan container `docker-compose up`