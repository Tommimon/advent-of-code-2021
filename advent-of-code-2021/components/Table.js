import React, { useEffect } from "react";
import { useState } from "react";
import InnerTable from "./InnerTable";

export default function Table(props) {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [Scoreboard, setScoreboard] = useState([]);

  useEffect(() => {
    fetch(
      "https://raw.githubusercontent.com/Tommimon/advent-of-code-2021/master/leaderboard.json"
    )
      .then((res) => res.json())
      .then(
        (result) => {
          setScoreboard(result);
          setIsLoaded(true);
        },
        (error) => {
          setScoreboard(true);
          setError(error);
        }
      );
  }, []);

  return (
    <div>
      <p>
        {/*console.log("ciao")}
        {console.log(Scoreboard.members)}
        {console.log(Scoreboard.members)}
        {Scoreboard.members ? Scoreboard.members[564880].name : ""}
        {Scoreboard.members
          ? Object.entries(Scoreboard.members).forEach(([key, value]) => {
              console.log(key, value.name);
            })
          : ""}
        {Scoreboard.toString()*/}
      </p>

      <table class="text-left w-full">
        <thead class="flex w-full">
          <tr class="flex items-center w-full bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
            <th class="py-3 w-full text-center ">Day</th>
            {Scoreboard.members
              ? Object.entries(Scoreboard.members).map(([key, value]) => (
                  <th class="py-3 px-2 w-full text-center">{value.name}</th>
                ))
              : ""}
          </tr>
        </thead>

        <tbody
          class=" flex flex-col justify-between overflow-y-scroll w-full text-gray-600 text-sm "
          style={{ height: 70 + "vh" }}
        >
          <InnerTable Scoreboard={Scoreboard} />
        </tbody>
      </table>
    </div>
  );
}
