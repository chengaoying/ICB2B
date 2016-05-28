FROM tomcat:8-jre8
RUN rm -rf /usr/local/tomcat/webapps
ADD ./target/ICB2B.war /usr/local/tomcat/webapps/ICB2B.war