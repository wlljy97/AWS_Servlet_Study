import React from 'react';

function Asynchronous(props) {

    /* 
        동기(Synchronous) : 순서대로 동작 o 
        비동기(unSynchronous) : 순서대로 동작 x
    
        콜백을 사용하는 이유 ?
        비동기 처리 안에서 내가 지정한 순서대로 함수 호출하려고!

    */
    let num = 0;

    const handleClick = () => {
        num++;

        const click1 = (num) => {
            console.log(`${num} click1!!!`);
        };
        const click1After = () => {
            console.log("click1 다음 실행!");
        };
        const click2 = (num) => {
            console.log(`${num} click2!!!`);
        };
        const click2After = () => {
            console.log("click2 다음 실행!");
        };

        const clickfx = (fx1, fx2) => {
            fx1(num);
            fx2();
        }

        // 1000은 1초
        setTimeout(clickfx,  Math.random(3) * 1000, click1, click1After) // 비동기
        click1After(); //동기
        setTimeout(clickfx,  Math.random(3) * 1000, click2, click2After) // 비동기
        click2After(); //동기

        // 비동기 안에서 비동기를 넣으면 동기가 된다.

    };

    // Promise - 콜백지옥 해결 방법중 하나
    // 비동기에서 성공과 실패를 분리해서 메소드를 수행한다는 것이다.

    // resolve, reject promise의 무조건적인 함수
    // resolve가 then 부분
    // reject는 error 처리 부분 catch
    // finally 는 무조건 실행
    const handleClick2= () => {
        const p1 = new Promise((resolve, reject) => {
            const num = Math.random(4);
            if(Math.round(num % 2) === 0){
                resolve("짝수");
            } else {
                reject(new Error("홀수"));
            }
        });

        p1
        .then(result => { // then이 있어야 실행이 됨
            console.log(result);
            return "첫번째 then의 리턴";
        })
        .then(result => {
            console.log(result);
        })
        .catch(error => {
            console.log(error);
        })
    }

    const handleClick3= () => {

        const printUser2 = () => {
            return new Promise((resolve, reject) => {
                resolve("유저2");
                reject(new Error("오류2"));
            });
        }

        printUser2().then(r => console.log(r));

        const printUser = async () => {
            try {
                await printUser2().then((r) => { // await은 async안에서만 사용가능
                    console.log(r);
                });
                throw new Error("오류 처리");
            } catch(error) {
                console.log(error);
            }
            return "유저1";
        }

        printUser().then(r => console.log(r));

    
    }


    return (
        <div>
            <button onClick={handleClick}>클릭</button>
            <button onClick={handleClick2}>클릭2</button>
            <button onClick={handleClick3}>클릭3</button>
        </div>
    );
}

export default Asynchronous;