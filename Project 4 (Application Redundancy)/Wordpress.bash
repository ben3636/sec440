#!/bin/bash
#Run me as root!

yum install php-gd -y
yum install httpd -y
yum install wget -y
yum install httpd php php-mysql -y
cd /var/www/html
wget https://wordpress.org/wordpress-5.1.1.tar.gz
tar -xzf wordpress-5.1.1.tar.gz
cp -r wordpress/* /var/www/html/
rm -rf wordpress
rm -rf wordpress-5.1.1.tar.gz
chmod -R 755 wp-content
chown -R apache:apache wp-content
cp /var/www/html/wp-config-sample.php /var/www/html/wp-config.php
curl https://api.wordpress.org/secret-key/1.1/salt
sleep 15
nano /var/www/html/wp-config.php
   ###---define('DB_NAME', 'wordpress');
   ###---define('DB_USER', 'wordpressuser');
   ###---define('DB_PASSWORD', 'password');
   ###---define('DB_HOST', '10.0.5.160');
yum install yum-utils –y
yum install epel-release –y
yum install http://rpms.remirepo.net/enterprise/remi-release-7.rpm -y
yum-config-manager ––enable remi–php70
yum install php php-common php-opcache php-mcrypt php-cli php-gd php-curl php-mysql –y
firewall-cmd --zone=public --permanent --add-port=80/tcp
firewall-cmd --reload
vi /etc/httpd/conf/httpd.conf
   ###---DirectoryIndex index.php
service httpd restart
