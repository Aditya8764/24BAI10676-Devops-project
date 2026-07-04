pipeline {
    agent any
    
    // Define our variables at the top so they are easy to change later
    environment {
        IMAGE_NAME = 'college-event-web'
        IMAGE_TAG  = 'v1'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pulls the latest code from GitHub using the URL you configured in the Jenkins UI
                checkout scm
            }
        }
        
        stage('Compile Java Application') {
            steps {
                echo "Packaging the Spring Boot application..."
                // Compiles the new .jar file so Docker doesn't use old cached code
                bat 'mvn clean package -DskipTests'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                echo "Building the new Docker image..."
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }
        
        stage('Deploy Application') {
            steps {
                echo "Removing the old container to free up port 8084..."
                // The || true prevents the pipeline from failing if the container doesn't exist yet
                bat "docker rm -f ${IMAGE_NAME} || true"
                
                echo "Deploying to the monitoring network with Spring Boot 4 Graphite config..."
                // The crucial deployment command containing our environment variable overrides
                bat "docker run -d --name ${IMAGE_NAME} --network monitoring -p 8084:8084 -e MANAGEMENT_GRAPHITE_METRICS_EXPORT_PROTOCOL=PLAINTEXT -e MANAGEMENT_GRAPHITE_METRICS_EXPORT_PORT=2003 ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }
}