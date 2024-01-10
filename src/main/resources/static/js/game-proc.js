window.onload = function () {
  const body = document.querySelector("body");
  const startButton = document.querySelector("#startbtn");
  const modal = document.querySelector(".modal");
  const form = document.getElementById("do");
  const slider = document.querySelector("#slider");
  let answer = document.getElementById("answer");
  let count = document.getElementById("count");
  
  let ansNote,
    usrNote,
    volume = tsw.gain(0.5);
  slider.value = 440;
  
  let isUsrOn = false;
  let isAnsOn = false;
  
  function changeBodyColor() {
    if (isAnsOn) {
      body.style.backgroundColor = "#800020";
    } else {
      body.style.backgroundColor = "#3f455d";
    }
  }
  
  function startNote(note) {
    if (note === ansNote) {
      isAnsOn = true;
    }
    if (note === usrNote) {
      isUsrOn = true;
    }
    
    tsw.connect(note, volume, tsw.speakers);
    note.start();
  }
  
  function disconnectNote(note) {
    setTimeout(function () {
      tsw.disconnect(note, volume);
      isAnsOn = false;
      changeBodyColor();
    }, 1000);
    setTimeout(count10, 1000);
  }
  
  function closeStartModal() {
    modal.style.display = "none";
  }
  
  function startAnsNote(event) {
    if (!isAnsOn) {
      closeStartModal();
      
      const randomFrequency = Math.floor(Math.random() * (880 - 196 + 1)) + 196;
      ansNote = tsw.osc(randomFrequency);
      answer.value = randomFrequency;
      
      setTimeout(function () {
        startNote(ansNote);
        ansNote.stop(tsw.now() + 1);
        changeBodyColor();
        disconnectNote(ansNote);
      }, 1000);
      
    }
  }
  
  function startUsrNote(event) {
    console.log(isUsrOn);
    if (!isUsrOn) {
      usrNote = tsw.osc(slider.value);
      startNote(usrNote);
    } else {
      usrNote.frequency(slider.value);
    }
  }
  
  function stopUsrNote(event) {
    if (isUsrOn) {
      usrNote.frequency(0);
    }
  }
  
  function count10() {
    let i = 3;
    
    const timeoutId = setInterval(() => {
      count.textContent = i.toString();
      if (i-- === 0) {
        form.submit();
        clearInterval(timeoutId)
      }
    }, 1000)
  }
  
  startButton.addEventListener("click", startAnsNote);

  slider.addEventListener("input", startUsrNote);
  slider.addEventListener("mouseup", stopUsrNote);
  slider.addEventListener("mouseover", stopUsrNote);
  
}