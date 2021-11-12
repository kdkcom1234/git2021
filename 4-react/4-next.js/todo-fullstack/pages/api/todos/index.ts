// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from "next";
import { prisma } from "../../../lib/db";
import { todo } from ".prisma/client";
import { toSerializable } from "../../../lib/json";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse<todo[] | todo | undefined>
) {
  if (req.method === "POST") {
    return post(req, res);
  }
}

async function post(
  req: NextApiRequest,
  res: NextApiResponse<todo | undefined>
) {
  const todoReq = req.body as todo;
  console.log(todoReq);

  const createdTodo = await prisma.todo.create({
    data: {
      memo: todoReq.memo,
      created_time: BigInt(new Date().getTime()),
    },
  });

  res.status(201).json(toSerializable<todo>(createdTodo));
}
