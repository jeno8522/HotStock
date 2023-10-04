"use client";

import { useState } from "react";
import { ThemeBar } from "@/components";
import { Pagination } from "@nextui-org/react";

type themeList = {
  themeId: number;
  name: string;
};

interface themeListProp {
  themeList: themeList[];
}

const ThemeList = ({ themeList }: themeListProp) => {
  const [page, setPage] = useState(1);
  const offset = (page - 1) * 10;
  const numPages = Math.ceil(themeList.length / 10);
  const handlePageClick = (newPage: number) => {
    if (newPage >= 1 && newPage <= numPages) {
      setPage(newPage);
    }
  };

  return (
    <div className="flex flex-col justify-center">
      <div className="gap-x-16 gap-y-10 py-10 lg:grid lg:grid-cols-2">
        {themeList.slice(offset, offset + 10).map((props, index) => (
          <ThemeBar key={index} {...props} />
        ))}
      </div>
      <div className="flex justify-center">
        <Pagination
          showControls
          total={numPages}
          initialPage={1}
          color="warning"
          onChange={(page) => handlePageClick(page)}
        />
      </div>
    </div>
  );
};

export default ThemeList;
