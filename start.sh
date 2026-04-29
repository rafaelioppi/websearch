#!/bin/bash

# Script de inicialização para backend + frontend

echo "🔧 Configurando ambiente para JDK 17..."

# Troca para JDK 17 usando SDKMAN (se disponível)
if command -v sdk >/dev/null 2>&1; then
  sdk use java 17.0.10-tem
fi

# Confirma versão do Java
java -version

# --- BACKEND ---
echo "🚀 Iniciando backend (Spring Boot)..."
cd backend || exit
mvn clean compile
mvn spring-boot:run &
BACK_PID=$!

# --- FRONTEND ---
echo "🌐 Iniciando frontend (React + Vite)..."
cd ../frontend || exit

# Instala dependências se necessário
if [ ! -d "node_modules" ]; then
  echo "📦 Instalando dependências do frontend..."
  npm install


# Sobe o servidor de desenvolvimento com Vite
npm run dev &
FRONT_PID=$!

# --- MONITOR ---
echo "✅ Backend rodando no PID $BACK_PID"
echo "✅ Frontend rodando no PID $FRONT_PID"

# Espera ambos os processos
wait $BACK_PID
wait $FRONT_PID
