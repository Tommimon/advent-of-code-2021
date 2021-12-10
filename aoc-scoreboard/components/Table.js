import React, { useEffect } from "react";
import { useState } from "react";
import InnerTable from "./InnerTable";
import HeaderTable from "./HeaderTable";

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
      <table class="text-left w-full block overflow-x-auto">
        <thead class="hidden lg:flex w-full">
          <tr class="flex items-center w-full bg-white text-gray-800 capitalize text-sm leading-normal">
            <th class="w-20 lg:w-full py-3 text-center ">Day</th>
            {Scoreboard.members ? <HeaderTable Scoreboard={Scoreboard} /> : ""}
          </tr>
        </thead>
        <tbody
          class=" table lg:block flex-col justify-between overflow-y-scroll w-full text-gray-600 text-sm "
          style={{ height: 65 + "vh" }}
        >
          <tr class="lg:hidden flex items-center w-full bg-white text-gray-800 capitalize text-sm leading-normal">
            <th class="w-20 lg:w-full py-3 text-center ">Day</th>
            {Scoreboard.members ? <HeaderTable Scoreboard={Scoreboard} /> : ""}
          </tr>
          <InnerTable Scoreboard={Scoreboard} />
        </tbody>
      </table>
    </div>
  );
}
