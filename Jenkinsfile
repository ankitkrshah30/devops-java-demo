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
					// Builds the image using the Dockerfile we just created
					sh "docker build -t ${DOCKER_IMAGE} ."
				}
			}
		}

		stage('Deploy Container') {
			steps {
				script {
					echo 'Deploying the Spring Boot full-stack application...'
					// Stop and remove the old container if it exists
					sh "docker stop ${CONTAINER_NAME} || true"
					sh "docker rm ${CONTAINER_NAME} || true"

					// Run the new container on port 8080
					sh "docker run -d -p 8081:8080 --name ${CONTAINER_NAME} ${DOCKER_IMAGE}"
				}
			}
		}
	}
}
