FROM tomcat:8-jre8
RUN rm -rf /usr/local/tomcat/webapps
ADD ./target/ICB2B-0.0.1.war /usr/local/tomcat/webapps/ICB2B-0.0.1.war