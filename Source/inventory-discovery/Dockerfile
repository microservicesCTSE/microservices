FROM openjdk:8-jre

RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.4.3-amd64.deb
RUN dpkg -i filebeat-6.4.3-amd64.deb
COPY filebeat.yml /etc/filebeat/filebeat.yml

USER root
RUN chmod go-w /etc/filebeat/filebeat.yml
RUN chown root /etc/filebeat/filebeat.yml
#
#RUN curl -L -O https://artifacts.elastic.co/downloads/beats/metricbeat/metricbeat-6.4.3-amd64.deb
#RUN dpkg -i metricbeat-6.4.3-amd64.deb
#COPY metricbeat.yml /etc/metricbeat/metricbeat.yml
#
#USER root
#RUN chmod go-w /etc/metricbeat/metricbeat.yml
#RUN chown root /etc/metricbeat/metricbeat.yml

RUN apt-get update && apt-get install -y supervisor
ADD supervisor.conf /etc/supervisor/conf.d/supervisord.conf

WORKDIR /etc/supervisor/conf.d

VOLUME /app/log

COPY target/inventory-discovery-1.0.jar /app/inventory-discovery.jar

ENTRYPOINT ["/usr/bin/supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]

EXPOSE 5001