document.getElementById('reg-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('name').value;
    alert(`Thank you, ${name}! Your registration for TechSymp has been received.`);
    this.reset();
});

// Simulate dynamic announcements fetching
setInterval(() => {
    const announcements = [
        "Hackathon teams must register by 1:00 PM.",
        "Keynote address moving to Main Auditorium.",
        "Lunch will be served at the cafeteria at 12:30 PM."
    ];
    const randomAnnouncement = announcements[Math.floor(Math.random() * announcements.length)];
    document.getElementById('announcement-ticker').innerText = randomAnnouncement;
}, 5000);