function clockWork(date) {
    const millis = parseInt(date);
    const hourHand = document.querySelector('.hourHand');
    const minuteHand = document.querySelector('.minuteHand');
    const secondHand = document.querySelector('.secondHand');
    let today = new Date(millis + 6000);

    function setDate(){
        const second = today.getSeconds();
        const secondDeg = ((second / 60) * 360) + 360;
        secondHand.style.transform = `rotate(${secondDeg}deg)`;

        const minute = today.getMinutes();
        const minuteDeg = ((minute / 60) * 360);
        minuteHand.style.transform = `rotate(${minuteDeg}deg)`;

        const hour = today.getHours();
        const hourDeg = ((hour / 12 ) * 360 );
        hourHand.style.transform = `rotate(${hourDeg}deg)`;
        today = new Date(today.getTime()+6000);

        document.getElementById("form:button").click();

        }

    setInterval(setDate, 6000);

}

