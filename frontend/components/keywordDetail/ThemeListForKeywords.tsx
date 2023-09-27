"use client";

import { Theme } from "@/types";
import Link from "next/link";
import { Select, SelectItem } from "@nextui-org/react";
import { useRouter } from "next/navigation";
import { useState } from "react";

interface themelist {
  theme: Theme;
}

interface ThemeListProps {
  keywordId: number;
  theme: Record<string, Theme>;
}

const ThemeListForKeywords = ({ keywordId, theme }: ThemeListProps) => {
  const themeArray = Object.values(theme);
  // console.log(themeArray);
  const [selectTheme, setSelectTheme] = useState<string>("");
  const router = useRouter();

  const handleChangeTheme = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedThemeId = e.target.value;
    setSelectTheme(selectedThemeId);
    // router.push(`/keyword/${keywordId}/${selectedThemeId}`);
  };

  return (
    <div className="w-full flex flex-col gap-4">
      {/* <div className="flex">
        {themelist.map((themeItem, index) => (
          <div className="" key={index}>
            <Link href={`/keyword/${keywordId}/${themeItem.id}`}>
              {themeItem.name}
            </Link>
          </div>
        ))}
      </div> */}
      <Select
        // label="테마를 선택해주세요"
        className=""
        // selectedKeys={selectTheme}
        onChange={handleChangeTheme}
        labelPlacement="outside"
        defaultSelectedKeys={[themeArray[0].themeId.toString()]}
      >
        {themeArray.map((theme) => (
          // <SelectItem key={theme.themeId} value={theme.themeId.toString()}>
          //   {theme.name}
          // </SelectItem>
          <SelectItem key={theme.themeId} value={theme.themeId.toString()}>
            <div>
              <Link href={`/keyword/${keywordId}/${theme.themeId}`} replace>
                {theme.name}
              </Link>
            </div>
          </SelectItem>
        ))}
      </Select>
    </div>
  );
};

export default ThemeListForKeywords;
