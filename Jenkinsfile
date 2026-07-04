pipeline {
    agent any

    tools {
        // Using the exact tool names you set up in Jenkins
        jdk 'JDK25'
        maven 'Maven-3.9.16'
    }

    environment {
        IMAGE_NAME = 'college-event-web'
        // Automatically tags each local image with the Jenkins Build Number
        IMAGE_TAG = "v${env.BUILD_ID}" 
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building the Spring Boot Application...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image Locally...'
                // Builds the image and stores it directly on your machine
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Applying Base Configurations...'
                bat 'kubectl apply -f k8s/deployment.yaml'
                bat 'kubectl apply -f k8s/service.yaml'
                
                echo 'Updating K8s with the new local version...'
                // Tells Kubernetes to grab the image we just built locally
                bat "kubectl set image deployment/techsymp-deployment techsymp-container=${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }
}