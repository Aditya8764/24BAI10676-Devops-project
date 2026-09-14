# graVITas 2026 - VIT Technical Symposium 🚀

A modern, highly available web application built to host the official Vellore Institute of Technology (VIT) annual techno-management fest: **graVITas 2026**.

## 🌟 Features

- **Modern VIT Branding**: Beautifully designed UI featuring glassmorphism, micro-animations, and the official VIT color palette (Dark Blue, White, Grey).
- **Responsive Design**: fully responsive layout ensuring a seamless experience across desktop and mobile devices.
- **Event Schedule & Speaker Roster**: Dynamically styled sections detailing keynotes, workshops (e.g., AI Generative Models), and events (e.g., 24-Hr Hackathon, RoboWars).
- **Automated Monitoring**: Fully integrated with a comprehensive DevOps monitoring stack:
  - **StatsD + Graphite**: JVM memory and CPU metrics are streamed directly via Micrometer.
  - **Grafana**: Real-time dashboards visualizing application performance.
  - **Nagios**: Active `/actuator/health` endpoint monitoring to ensure high availability.

## 🛠️ Technology Stack

- **Backend**: Java 25, Spring Boot 4.1.0 (with Actuator & Micrometer)
- **Frontend**: HTML5, Vanilla CSS3 (Custom Design System), JavaScript
- **Infrastructure**: Docker, Docker Compose
- **Metrics & Observability**: Graphite, Grafana, Nagios, StatsD

## 🚀 Getting Started

### Prerequisites
- [Docker](https://www.docker.com/products/docker-desktop/) and Docker Compose installed on your system.
- Java 25 (if building locally outside of Docker).

### Running the Application

The easiest way to start the application and its monitoring stack is via Docker Compose.

1. Clone the repository and navigate to the project directory:
   ```bash
   cd college_event_web
   ```

2. Build and start the containers in detached mode:
   ```bash
   docker-compose up -d --build
   ```

3. Access the services:
   - **Main Website**: [http://localhost:8084](http://localhost:8084)
   - **Actuator Health/Metrics**: [http://localhost:8084/actuator](http://localhost:8084/actuator)
   - **Grafana Dashboards**: Typically available on the port configured in your local `monitoring` network setup.

## 📊 Monitoring Integration

This project is configured to work seamlessly with an external monitoring stack container setup.
- **StatsD Export**: The Spring Boot application is explicitly configured (via `CustomStatsdConfig`) to push metrics to a host named `graphite` on UDP port `8125`.
- **Nagios Configuration**: Nagios monitors the container via the `check_http` plugin pointing to port `8084` at `/actuator/health`.

## 📁 Project Structure

```
college_event_web/
├── src/
│   ├── main/
│   │   ├── java/com/example/college_event_web/
│   │   │   ├── CollegeEventWebApplication.java
│   │   │   └── CustomStatsdConfig.java          # Overrides Micrometer to point to Graphite
│   │   └── resources/
│   │       ├── application.properties           # Spring Boot & Actuator configuration
│   │       └── static/
│   │           ├── index.html                   # Main landing page
│   │           ├── css/style.css                # VIT themed stylesheet
│   │           └── js/script.js                 # Interactive logic
├── docker-compose.yml                           # Docker configuration
├── Dockerfile                                   # Eclipse Temurin Java 25 build
├── pom.xml                                      # Maven dependencies
└── dashboard.json                               # Pre-configured Grafana dashboard for JVM/CPU
```

## 📝 License

This project is created for educational and demonstration purposes. &copy; 2026 Vellore Institute of Technology. All rights reserved.
