FROM node:20-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm ci

# Copiar archivos de Prisma
COPY prisma ./prisma

# Generar Prisma Client
RUN npx prisma generate

# Copiar código fuente
COPY src ./src

# Exponer puerto de la API
EXPOSE 5000

# Iniciar servidor
CMD ["npm", "start"]