FROM nginx

COPY ssl/server.crt /etc/ssl/certs/
COPY ssl/server.key /etc/ssl/private/

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY auth/.htpasswd /etc/nginx/conf.d/.htpasswd

EXPOSE 443