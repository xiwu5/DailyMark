/*
    Script for index.html
 */

document.addEventListener('DOMContentLoaded', function() {
    // Initialize FullCalendar
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        initialDate: new Date(), // Set the initial date to today
        selectable: true,
        dateClick: function(info) {
            // AJAX call to check for marks on the selected date
            fetch('/marks/check?date=' + info.dateStr)
                .then(response => response.json())
                .then(data => {
                    // Redirect based on the server's response
                    window.location.href = data.url;
                })
                .catch(error => console.error('Error:', error));
        },
        events: [
            {
                title: 'Category 1',
                start: '2024-06-09',
                display: 'background',
                classNames: ['checklist-item'],
                extendedProps: {
                    category: 'Example'
                }
            },
            {
                title: 'Category 2',
                start: '2024-06-10',
                display: 'background',
                classNames: ['checklist-item'],
                extendedProps: {
                    category: 'Example'
                }
            }
        ],
        eventContent: function(arg) {
            let contentEl = document.createElement('div');
            contentEl.classList.add('checklist-item');

            let checkboxEl = document.createElement('input');
            checkboxEl.setAttribute('type', 'checkbox');
            contentEl.appendChild(checkboxEl);

            let titleEl = document.createElement('span');
            titleEl.innerText = arg.event.extendedProps.category;
            contentEl.appendChild(titleEl);

            return { domNodes: [contentEl] };
        }
    });

    calendar.render();
    // Sidebar toggle functionality
    const sidebar = document.querySelector('.sidebar');
    const menuIcon = document.getElementById('menu-icon');
    const content = document.querySelector('.content');

    menuIcon.addEventListener('click', function() {
        sidebar.classList.toggle('active');
        if (sidebar.classList.contains('active')) {
            // Adjust content margin when sidebar is active
            content.style.marginLeft = '250px';
        } else {
            // Reset content margin when sidebar is inactive
            content.style.marginLeft = '0';
        }
    });

    // Close sidebar when clicking outside of it
    document.addEventListener('click', function(event) {
        if (!sidebar.contains(event.target) && event.target !== menuIcon) {
            sidebar.classList.remove('active');
            content.style.marginLeft = '0';
        }
    });


});