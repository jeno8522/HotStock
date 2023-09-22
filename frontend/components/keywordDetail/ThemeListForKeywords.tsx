import { Theme } from "@/types";
import Link from "next/link";

interface themeListProps {
  themelist: Theme[];
  keywordId: number;
}

const ThemeListForKeywords = ({ themelist, keywordId }: themeListProps) => {
  return (
    <div>
      {/* <div className="flex">
        {themelist.map((themeItem, index) => (
          <div className="" key={index}>
            <Link href={`/keyword/${keywordId}/${themeItem.id}`}>
              {themeItem.name}
            </Link>
          </div>
        ))}
      </div> */}
    </div>
  );
};

export default ThemeListForKeywords;
