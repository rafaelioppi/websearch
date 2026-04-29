#!/bin/bash

# reboot.sh - encerra e reinicia o backend Spring Boot

echo "Encerrando processos Java/Spring Boot..."
pkill -f 'org.springframework.boot'  # mata qualquer processo rodando o Spring Boot

echo "Esperando 2 segundos..."
sleep 2


cd backend || exit

echo "Reiniciando aplicação..."
mvn spring-boot:run

cd front end || exit

pkill -f vite
echo "🌐 Iniciando frontend (React + Vite)..."
cd ../frontend || exit

# Instala dependências se necessário
if [ ! -d "node_modules" ]; then
  echo "📦 Instalando dependências do frontend..."
  npm install


# Sobe o servidor de desenvolvimento com Vite
npm run dev &