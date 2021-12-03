export default function Table(props) {
  const days = [
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
    22, 23, 24, 25,
  ];
  const NameToFolder = {
      "Alessandro Nazzari" : false,
      "Marco Molè" : "marcomole00",
      "Matteo Negro" : "MatteoBlack",
      "riccardo-negri" : "riccardo_negri",
      "Gonduls" : "Gonduls",
      "Tommimon" : "tommimon",
      "Davide Palmiotti" : "mynam3isg00d",
      "marcoparadina": "marcoparadina",
      "gingervi": false,
      "alexeats": false,
      "john-galt-10": "john_galt_10"

  }

  return (
    <div>
      {days.map((day) => (
        <tr class={"flex w-full" + (day%2 === 1 ? "border-b border-gray-200 bg-white hover:bg-gray-100" : "border-b border-gray-200 bg-gray-50 hover:bg-gray-100")}>
          <td class="w-full py-4 text-center">{day}</td>
          {props.Scoreboard.members
            ? Object.entries(props.Scoreboard.members).map(([key, value]) => (
                <th class="w-full py-4 text-center">
                  {value.completion_day_level[day]
                    ? value.completion_day_level[day][1]
                      ? value.completion_day_level[day][2]
                        ? NameToFolder[value.name] ? <a href={"https://github.com/Tommimon/advent-of-code-2021/tree/master/"+NameToFolder[value.name]+"/d"+(day<10 ? "0" : "")+day} target="_blank" class="text-yellow-400">**</a> : "**"
                        : NameToFolder[value.name] ? <a href={"https://github.com/Tommimon/advent-of-code-2021/tree/master/"+NameToFolder[value.name]+"/d"+(day<10 ? "0" : "")+day} target="_blank" class="text-yellow-400">*</a> : "*"
                      : "-"
                    : "-"}
                </th>
              ))
            : ""}
        </tr>
      ))}
    </div>
  );
}
