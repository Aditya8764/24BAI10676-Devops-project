pipeline {
    agent any
    
    environment {
        IMAGE_NAME = 'college-event-web'
        IMAGE_TAG  = 'v1'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        
        stage('Compile Java Application') {
            steps {
                echo "Packaging the Spring Boot application..."
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
                echo "Removing the old container..."
                bat "docker rm -f ${IMAGE_NAME} || true"
                
                echo "Deploying with forced JVM Graphite configuration..."
                // Using multiple -e flags instead of a single string with spaces/quotes 
                // to prevent Windows command parsing errors.
                bat "docker run -d --name ${IMAGE_NAME} --network monitoring -p 8084:8084 -e JAVA_TOOL_OPTIONS=\"-Dmanagement.graphite.metrics.export.host=graphite -Dmanagement.graphite.metrics.export.port=2003 -Dmanagement.graphite.metrics.export.protocol=PLAINTEXT\" ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }
}