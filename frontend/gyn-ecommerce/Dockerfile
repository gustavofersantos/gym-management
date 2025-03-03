# Usa a imagem oficial do Nginx como base
FROM nginx:latest

# Remove a configuração padrão do Nginx
RUN rm -rf /etc/nginx/conf.d

# Copia a nova configuração personalizada do Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copia os arquivos estáticos do projeto front-end para a pasta padrão do Nginx
COPY build /usr/share/nginx/html

# Expõe a porta 80 para acesso ao servidor Nginx
EXPOSE 80

# Comando para iniciar o Nginx em modo foreground
CMD ["nginx", "-g", "daemon off;"]
