import React from "react";
import Link from "next/link";

interface ThemeProps {
  themeId: number;
  name: string;
}

const ThemeBar: React.FC<ThemeProps> = ({themeId, name}) => {
  return (
    <div className="w-1/3 mx-auto items-center">
      <div className="flex justify-between rounded-md my-3 border-2 border-gray-300 p-4">
        <div className="flex items-center px-3">
          <div className="pr-2">No.{themeId}</div>
          <div className="font-bold w-52 px-2">
            <div className="truncate">{name}</div>
          </div>
        </div>
        <div className="font-bold px-2 mr-5">
          <Link href={`/theme/${themeId}`}>
            <button className="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-md shadow-md transition duration-300 ease-in-out">
              상세보기
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default ThemeBar;
