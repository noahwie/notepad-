import { render, screen, fireEvent } from "@testing-library/react";
import FolderItem from "../FolderItem";

describe("FolderItem", () => {
  const sampleFolder = { id: 1, name: "Reiseplanung" };

  it("renders folder name", () => {
    render(<FolderItem folder={sampleFolder} onClick={() => {}} onDelete={() => {}} />);
    expect(screen.getByText("Reiseplanung")).toBeInTheDocument();
  });

  it("calls onClick when name is clicked", () => {
    const handleClick = vi.fn();
    render(<FolderItem folder={sampleFolder} onClick={handleClick} onDelete={() => {}} />);
    fireEvent.click(screen.getByText("Reiseplanung"));
    expect(handleClick).toHaveBeenCalledTimes(1);
  });

  it("calls onDelete when X is clicked", () => {
    const handleDelete = vi.fn();
    render(<FolderItem folder={sampleFolder} onClick={() => {}} onDelete={handleDelete} />);
    const deleteButton = screen.getByRole("button");
    fireEvent.click(deleteButton);
    expect(handleDelete).toHaveBeenCalledTimes(1);
  });
});
