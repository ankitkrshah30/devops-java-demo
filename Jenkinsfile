pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "spring-boot-crud-demo:latest"
        CONTAINER_NAME = "java-demo-container"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Jenkins checks out the code from your repository
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Java App and Docker Image...'
                    // Changed 'sh' to 'bat' for Windows
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Deploy Container') {
            steps {
                script {
                    echo 'Deploying the Spring Boot full-stack application...'
                    // Changed 'sh' to 'bat' and fixed the error handling for Windows
                    bat "docker stop ${CONTAINER_NAME} || echo 'No container to stop'"
                    bat "docker rm ${CONTAINER_NAME} || echo 'No container to remove'"

                    // Run the new container on port 8081
                    bat "docker run -d -p 8081:8080 --name ${CONTAINER_NAME} ${DOCKER_IMAGE}"
                }
            }
        }
    }
}