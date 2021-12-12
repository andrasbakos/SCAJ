# Create image based on the Skaffolder Java-MongoDB image
FROM skaffolder/java-mongo-base

# Change directory so that our commands run inside this new directory
WORKDIR /app

# Link current folder to container
RUN rm -Rf /app/*
ADD . /app/

# Serve the app
RUN mvn install

# Serve the app
CMD ["mvn", "spring-boot:run"]