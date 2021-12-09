export default function Table(props) {
  const ExtensionsToLanguage = {
    ".java": "Java",
    ".py": "Python",
    ".c": "C",
    ".cpp": "C++",
    ".asm": "Assembly",
    ".sql": "SQL",
  };

  const LangToIcon = {
    Java: "devicon-java-plain",
    Python: "devicon-python-plain",
    C: "devicon-c-plain",
    "C++": "devicon-cpp-plain",
    Assembly: "devicon-appcelerator-plain",
    SQL: "devicon-mysql-plain",
  };

  const getProgrammmingLanguage = (tree, name, day, NameToFolder) => {
    const matchList = [];
    Object.entries(tree).forEach(([key, value]) =>
      value.path.indexOf(
        NameToFolder[name] + "/d" + (day < 10 ? "0" : "") + day
      ) != -1
        ? matchList.push(value.path)
        : void 0
    );

    for (const [key, value] of Object.entries(ExtensionsToLanguage)) {
      for (const file of matchList) {
        if (file.indexOf(key) > -1) {
          return <i class={LangToIcon[value] + " colored text-2xl"}></i>;
        }
      }
    }

    return "?";
  };

  return (
    <div class="flex justify-center text-gray-60">
      <p>
        {" "}
        {props.GitHubRepo.tree
          ? getProgrammmingLanguage(
              props.GitHubRepo.tree,
              props.name,
              props.day,
              props.NameToFolder
            )
          : void 0}
      </p>
    </div>
  );
}
