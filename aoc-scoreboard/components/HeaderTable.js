export default function HeaderTable(props) {
  return (
    <>
      {Object.entries(props.Scoreboard.members).map(([key, value]) => (
        <th class="w-20 lg:w-full py-3 px-2 text-center overflow-x-hidden">
          <p>{value.name}</p>
          <p class="flex items-center justify-center">({value.stars} <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4 text-yellow-400"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>)</p>
          
        </th>
      ))}
    </>
  );
}
